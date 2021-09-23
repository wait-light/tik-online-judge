package top.adxd.tikonlinejudge.executor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.config.CLanguageCodeExecuteConfig;
import top.adxd.tikonlinejudge.executor.config.CppCodeExecuteConfig;
import top.adxd.tikonlinejudge.executor.config.JavaCodeExecuteConfig;
import top.adxd.tikonlinejudge.executor.config.PythonCodeExecuteConfig;
import top.adxd.tikonlinejudge.executor.constant.LanguageConstant;
import top.adxd.tikonlinejudge.executor.entity.ProblemData;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.exception.runtime.LanguageUnsupportException;
import top.adxd.tikonlinejudge.executor.service.ICodeExecutor;
import top.adxd.tikonlinejudge.executor.service.ICodeJudge;
import top.adxd.tikonlinejudge.executor.service.IFileReaderWriter;
import top.adxd.tikonlinejudge.executor.service.IProblemDataService;
import top.adxd.tikonlinejudge.executor.vo.*;

import java.util.ArrayList;
import java.util.List;

@Service("codeJudge")
public class CodeJudge implements ICodeJudge {
    @Autowired
    private ICodeExecutor javaCodeExecutor;
    @Autowired
    private ICodeExecutor cppCodeExecutor;
    @Autowired
    private ICodeExecutor pythonCodeExecutor;
    @Autowired
    private ICodeExecutor cLanguageCodeExecutor;
    @Autowired
    private IFileReaderWriter fileReaderWriter;
    @Autowired
    private JavaCodeExecuteConfig javaCodeExecuteConfig;
    @Autowired
    private CppCodeExecuteConfig cppCodeExecuteConfig;
    @Autowired
    private CLanguageCodeExecuteConfig cLanguageCodeExecuteConfig;
    @Autowired
    private PythonCodeExecuteConfig pythonCodeExecuteConfig;
    @Autowired
    private IProblemDataService problemDataService;

    /**
     * 根据语言类型返回其源文件对应的地址
     *
     * @param type
     * @return
     */
    private String getLanguageFilePath(int type) {
        if (type == LanguageConstant.C_LANGUAGE) {
            return cLanguageCodeExecuteConfig.getCodeFullPath();
        }
        if (type == LanguageConstant.JAVA_LANGUAGE) {
            return javaCodeExecuteConfig.getClassFullPath();
        }
        if (type == LanguageConstant.PYTHON_LANGUAGE) {
            return pythonCodeExecuteConfig.getCodeFullPath();
        }
        if (type == LanguageConstant.CPP_LANGUAGE) {
            return cppCodeExecuteConfig.getCodeFullPath();
        }
        throw new LanguageUnsupportException("language unsupport");
    }

    /**
     * 根据语言类型返回其执行器
     *
     * @param type
     * @return
     */
    private ICodeExecutor getLanguageExecutor(int type) {
        if (type == LanguageConstant.C_LANGUAGE) {
            return cLanguageCodeExecutor;
        }
        if (type == LanguageConstant.JAVA_LANGUAGE) {
            return javaCodeExecutor;
        }
        if (type == LanguageConstant.PYTHON_LANGUAGE) {
            return pythonCodeExecutor;
        }
        if (type == LanguageConstant.CPP_LANGUAGE) {
            return cppCodeExecutor;
        }
        throw new LanguageUnsupportException("language unsupport");
    }

    @Override
    public List<JudgeResult> judge(Submit submit) {
        String languageFilePath = getLanguageFilePath(submit.getLanguageType());
        //将代码写入源文件
        fileReaderWriter.writer(languageFilePath, submit.getContent(), false);
        ICodeExecutor languageExecutor = getLanguageExecutor(submit.getLanguageType());
        //TODO 根据问题获取对应的执行时常
        List<ProblemData> problemDataList = problemDataService.getProblemDataList(submit.getProblemId());
        List<JudgeResult> judgeResultList = new ArrayList<>();
        ExecuteInput executeInput = new ExecuteInput();
        int executeCount = problemDataList.size();
        String correctOutput = null;
        //第一次进行编译，后续执行直接运行
        if (problemDataList.size() > 0) {
            ProblemData problemData = problemDataList.get(0);
            executeInput.setInput(problemData.getInput());
            //TODO 时间还没设置
            executeInput.setTime(1000L);
            correctOutput = problemData.getOutput();
        }
        ExecuteResult executeResult = languageExecutor.execute(executeInput, true);
        JudgeResult executeWithCompile = JudgeResult.parse(executeResult, submit.getId(), correctOutput,1000L);
        judgeResultList.add(executeWithCompile);
        //编译错误，后续不再执行各个数据
        if (ExecuteStatus.COMPILE_ERROR.equals(executeResult.getExecuteStatus())) {
            for (int i = 1; i < executeCount; i++) {
                judgeResultList.add(executeWithCompile);
            }
        } else {
            for (int i = 1; i < executeCount; i++) {
                ProblemData problemData = problemDataList.get(i);
                executeInput.setInput(problemData.getInput());
                //TODO 时间还没设置
                executeInput.setTime(1000L);
                ExecuteResult execute = languageExecutor.execute(executeInput, false);
                judgeResultList.add(JudgeResult.parse(execute, submit.getId(), problemData.getOutput(),1000L));
            }
        }
        return judgeResultList;
    }
}
