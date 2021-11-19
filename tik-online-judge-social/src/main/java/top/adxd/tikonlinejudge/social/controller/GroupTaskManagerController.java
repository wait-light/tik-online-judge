package top.adxd.tikonlinejudge.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.dto.GroupTaskDto;
import top.adxd.tikonlinejudge.social.service.IGroupTaskManagerService;

@RestController
@RequestMapping("/social/group-task-manager")
public class GroupTaskManagerController {
    @Autowired
    private IGroupTaskManagerService groupTaskManagerService;


    @GetMapping("/{groupId}")
    public CommonResult tasks(@PathVariable("groupId") Long groupId) {
        return groupTaskManagerService.tasks(groupId);
    }

    @PostMapping("/{groupId}")
    public CommonResult taskAdd(@PathVariable("groupId") Long groupId, @RequestBody GroupTaskDto groupTaskDto) {
        return groupTaskManagerService.addTask(groupId, groupTaskDto);
    }

    @PutMapping("/{groupId}/{taskId}")
    public CommonResult taskUpdate(@PathVariable("groupId") Long groupId, @PathVariable("taskId") Long taskId, @RequestBody GroupTaskDto groupTaskDto) {
        return groupTaskManagerService.updateTask(groupId, taskId, groupTaskDto);
    }

    @DeleteMapping("/{groupId}/{taskId}")
    public CommonResult taskDelete(@PathVariable("groupId") Long groupId, @PathVariable("taskId") Long taskId) {
        return groupTaskManagerService.deleteTask(groupId, taskId);
    }
}
