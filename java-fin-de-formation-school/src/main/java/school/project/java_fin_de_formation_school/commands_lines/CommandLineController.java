package school.project.java_fin_de_formation_school.commands_lines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/command-line")
public class CommandLineController {

    @Autowired
    private CommandLineService commandLineService;

    @GetMapping
    public List<CommandLineEntity> getAllCommandLineEntities() {
        return commandLineService.getAllCommandLineEntities();
    }

    @GetMapping("/{id}")
    public CommandLineEntity getCommandLineEntityById(@PathVariable String id) {
        return commandLineService.getCommandLineEntityById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<CommandLineEntity> getCommandLinesByOrderId(@PathVariable String orderId) {
        return commandLineService.getCommandLinesByOrderId(orderId);
    }

    @PostMapping
    public CommandLineEntity createCommandLineEntity(@RequestBody CommandLineDto commandLineEntity) {
        return commandLineService.createCommandLineEntity(commandLineEntity);
    }

    @PutMapping("/{id}")
    public CommandLineEntity updateCommandLine(@PathVariable String id,
            @RequestBody CommandLineEntity commandLineEntity) {
        return commandLineService.updateCommandLine(id, commandLineEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteCommandLineEntity(@PathVariable String id) {
        commandLineService.deleteCommandLineEntity(id);
    }
}
