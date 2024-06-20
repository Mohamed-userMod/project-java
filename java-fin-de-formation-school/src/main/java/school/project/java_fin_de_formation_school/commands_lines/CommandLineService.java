package school.project.java_fin_de_formation_school.commands_lines;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.project.java_fin_de_formation_school.orders.OrderEntity;
import school.project.java_fin_de_formation_school.orders.OrderRepository;
import school.project.java_fin_de_formation_school.products.ProductEntity;
import school.project.java_fin_de_formation_school.products.ProductRepository;

@Service
public class CommandLineService {

    @Autowired
    private CommandLineRepository commandLineRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CommandLineEntity> getCommandLinesByOrderId(String orderId) {
        return commandLineRepository.findByOrderId(orderId);
    }

    public List<CommandLineEntity> getAllCommandLineEntities() {
        return commandLineRepository.findAll();
    }

    public CommandLineEntity getCommandLineEntityById(String idCommandLine) {
        return commandLineRepository.findById(idCommandLine).orElse(null);
    }

    public CommandLineEntity createCommandLineEntity(CommandLineDto commandLineDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(commandLineDTO.order_id());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(commandLineDTO.product_id());

        CommandLineEntity commandLineEntity = new CommandLineEntity();
        commandLineEntity.setOrder(orderEntity);
        commandLineEntity.setProduct(productEntity);
        commandLineEntity.setQuantity(commandLineDTO.quantity());

        commandLineEntity = commandLineRepository.save(commandLineEntity);

        return commandLineEntity;
    }

    public CommandLineEntity updateCommandLine(String id, CommandLineEntity commandLineEntity) {
        Optional<CommandLineEntity> optionalCommandLine = commandLineRepository.findById(id);

        if (optionalCommandLine.isPresent()) {
            CommandLineEntity existingCommandLine = optionalCommandLine.get();

            if (commandLineEntity.getQuantity() != 0) {
                existingCommandLine.setQuantity(commandLineEntity.getQuantity());
            }

            if (commandLineEntity.getOrder() != null) {
                Optional<OrderEntity> optionalOrder = orderRepository.findById(commandLineEntity.getOrder().getId());
                if (optionalOrder.isPresent()) {
                    existingCommandLine.setOrder(optionalOrder.get());
                } else {
                    throw new RuntimeException("Order not found with id: " + commandLineEntity.getOrder().getId());
                }
            }

            if (commandLineEntity.getProduct() != null) {
                Optional<ProductEntity> optionalProduct = productRepository
                        .findById(commandLineEntity.getProduct().getId());
                if (optionalProduct.isPresent()) {
                    existingCommandLine.setProduct(optionalProduct.get());
                } else {
                    throw new RuntimeException("Product not found with id: " + commandLineEntity.getProduct().getId());
                }
            }

            return commandLineRepository.save(existingCommandLine);
        } else {
            throw new RuntimeException("CommandLine not found with id: " + id);
        }
    }

    public void deleteCommandLineEntity(String idCommandLine) {
        commandLineRepository.deleteById(idCommandLine);
    }
}
