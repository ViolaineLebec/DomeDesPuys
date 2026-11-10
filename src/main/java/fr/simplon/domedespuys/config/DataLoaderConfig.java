package fr.simplon.domedespuys.config;

import com.github.javafaker.Faker;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.entities.*;
import fr.simplon.domedespuys.repositories.*;
import fr.simplon.domedespuys.utils.Slugger;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@Component
@AllArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private BrandRepository brandRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private ImageRepository imageRepository;
    private OrderProductRepository orderProductRepository;
    private Slugger slugger;

    @Override
    public void run(String @NonNull ... args) {
        createUsers();
        createBrands();
        createAddresses();
        createProducts();
        createImages();
        createOrders();
        createOrderProducts();
    }

    private void createUsers() {
        if (0 == userRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 100; i++) {
                User user = new User();
                user.setFirstname(faker.name().firstName());
                user.setLastname(faker.name().lastName());
                String email = faker.internet().emailAddress((user.getFirstname() + "." + user.getLastname()).toLowerCase());
                user.setEmail(email);
                user.setPassword(faker.internet().password(12, 20));
                if (hasProbability(2, 3)) {
                    user.setPhone(faker.phoneNumber().phoneNumber());
                }
                LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 5).getTime()).toLocalDate().atTime(0, 0);
                user.setCreatedAt(localDate);
                userRepository.save(user);
            }

            userRepository.flush();
        }
    }

    private void createBrands() {
        if (0 == brandRepository.count()) {
            Faker faker = new Faker();
            for (int i = 0; i < 30; i++) {
                Brand brand = new Brand();
                brand.setLabel(faker.company().name());
                if (hasProbability(2, 3)) {
                    brand.setLogo(faker.company().logo());
                }
                brand.setSlug(slugger.slugify(brand.getLabel()));
                brandRepository.save(brand);
            }

            brandRepository.flush();
        }
    }

    private void createAddresses() {
        if (0 == addressRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            for (int i = 0; i < 100; i++) {
                Address address = new Address();
                address.setCity(faker.address().city());
                address.setCountry("France");

                address.setLine(faker.address().streetAddress());
                address.setZipCode(faker.address().zipCode());

                Random random = new Random();
                Long userId = random.nextLong(1, 100);
                Optional<User> optionalUser = userRepository.findById(userId);
                optionalUser.ifPresent(address::setUser);

                if (hasProbability(3, 5)) {
                    address.setFirstname(address.getUser().getFirstname());
                    address.setLastname(address.getUser().getLastname());
                    address.setPhone(address.getUser().getPhone());
                } else {
                    address.setFirstname(faker.name().firstName());
                    address.setLastname(faker.name().lastName());
                    address.setPhone(faker.phoneNumber().phoneNumber());
                }

                if (hasProbability(1, 3)) {
                    address.setExtraLine(faker.address().buildingNumber());
                }

                int year = address.getUser().getCreatedAt().getYear();
                int currentYear = LocalDate.now().getYear();
                address.setCreatedAt(getRandomDate(faker, 0, currentYear - year));

                addressRepository.save(address);
            }
            userRepository.flush();
        }
    }

    private void createProducts() {
        if (0 == productRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            for (int i = 0; i < 200; i++) {
                Product product = new Product();

                Random random = new Random();
                Long brandId = random.nextLong(1, 30);
                Optional<Brand> optionalBrand = brandRepository.findById(brandId);
                optionalBrand.ifPresent(product::setBrand);

                if (hasProbability(7, 10)) {
                    product.setDescription(faker.lorem().paragraph());
                }

                product.setCreatedAt(getRandomDate(faker, 0, 10));

                product.setLabel(faker.commerce().productName());
                product.setPrice(random.nextLong(100, 99999));
                product.setSlug(slugger.slugify(product.getLabel()));

                productRepository.save(product);

            }

            productRepository.flush();
        }
    }

    private void createImages() {
        if (0 == imageRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            for (int i = 0; i < 100; i++) {
                Image image = new Image();

                image.setExtension("jpg");

                Random random = new Random();
                int imagePathNumber = random.nextInt(1, 999);
                image.setPath("https://picsum.photos/400/300?r=" + imagePathNumber);

                image.setIsDefault(random.nextBoolean());
                Long productId = random.nextLong(1, 200);
                Optional<Product> optionalProduct = productRepository.findById(productId);
                optionalProduct.ifPresent(image::setProduct);

                imageRepository.save(image);

            }

            imageRepository.flush();
        }
    }

    public void createOrders() {
        if (0 == orderRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 500; i++) {
                Order order = new Order();
                String tmpNumber;
                if (i < 10) {
                    tmpNumber = "0000000000" + i;
                } else if (i < 100) {
                    tmpNumber = "000000000" + i;
                } else {
                    tmpNumber = "00000000" + i;
                }
                String number = "PUR-" + LocalDateTime.now().getYear() + "-" + tmpNumber;
                order.setNumber(number);

                User user;
                List<Address> addresses;
                Random random = new Random();

                do {
                    Long idUser = random.nextLong(1, 100);
                    Optional<User> optionalUser = userRepository.findById(idUser);
                    user = optionalUser.orElseThrow();
                    addresses = addressRepository.findAllByUser(user);
                } while (addresses.isEmpty());

                order.setUser(user);
                order.setAddress(addresses.getFirst());

                int year = user.getCreatedAt().getYear();
                int currentYear = LocalDate.now().getYear();
                int yearBetween = currentYear - year;
                order.setCreatedAt(getRandomDate(faker, 0, yearBetween));

                if (yearBetween > 1) {
                    order.setStatus(EnumPurchaseStatus.DELIVERED.getValue());
                } else {
                    int status = faker.number().numberBetween(1, 5);
                    switch (status) {
                        case 1:
                            order.setStatus(EnumPurchaseStatus.PENDING.getValue());
                            break;
                        case 2:
                            order.setStatus(EnumPurchaseStatus.PAID.getValue());
                            break;
                        case 3:
                            order.setStatus(EnumPurchaseStatus.SHIPPED.getValue());
                            break;
                        default:
                            order.setStatus(EnumPurchaseStatus.CANCELLED.getValue());
                            break;
                    }
                }

                orderRepository.save(order);
            }
            orderRepository.flush();
        }
    }

//    private void createOrders() {
//        if (0 == orderRepository.count()) {
//            Faker faker = new Faker(Locale.of("fr"));
//            for (int i = 1; i < 301; i++) {
//                Order order = new Order();
//
//                String tmpNumber = "";
//                if (i < 10) {
//                    tmpNumber = "00000000000" + i;
//                } else if (i < 100) {
//                    tmpNumber = "0000000000" + i;
//                } else if (i < 1000) {
//                    tmpNumber = "000000000" + i;
//                }
//                String number = "PUR-" + LocalDateTime.now().getYear() + "-" + tmpNumber;
//                order.setNumber(number);
//
//                order.setCreatedAt(getRandomDate(faker, 0, 8));
//
//                Random random = new Random();
//
//                User user;
//                List<Address> addresses;
//                do {
//                    Long idUser = random.nextLong(1,10);
//                    Optional<User> optionalUser = userRepository.findById(idUser);
//                    user = optionalUser.orElseThrow();
//
//                    order.setUser(user);
//
//                    addresses = addressRepository.findAllByUser(user);
//                } while (addresses.isEmpty());
//                order.setAddress(addresses.getFirst());
//
//
//                orderRepository.save(order);
//
//            }
//            orderRepository.flush();
//        }
//    }

    private void createOrderProducts() {
        if (0 == orderProductRepository.count()) {
            List<Order> orders = orderRepository.findAll();
            for (Order order : orders) {
                createOrderProductByOrder(order);

                do {
                    createOrderProductByOrder(order);
                } while (hasProbability(1, 3));
            }

            orderProductRepository.flush();
        }
    }

    private void createOrderProductByOrder(Order order) {
        Faker faker = new Faker(Locale.of("fr"));
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setQuantity(faker.number().numberBetween(1, 6));

        Optional<Product> productOptional = productRepository.findById(faker.number().numberBetween(1L, 300L));
        productOptional.ifPresent(orderProduct::setProduct);

        long priceProduct = orderProduct.getProduct().getPrice();
        orderProduct.setPrice(priceProduct);
        if (hasProbability(1, 6)) {
            orderProduct.setPrice((long) (priceProduct * 1.2));
        }

        orderProductRepository.save(orderProduct);
    }

//    private void createOrderProducts() {
//        if (0 == orderProductRepository.count()) {
//            Faker faker = new Faker(Locale.of("fr"));
//            for (int i = 1; i < 301; i++) {
//                OrderProduct orderProduct = new OrderProduct();
//
//                Random random = new Random();
//                orderProduct.setQuantity(random.nextInt(1,10));
//
//                Long orderId = random.nextLong(1, 200);
//                Optional<Order> optionalOrder = orderRepository.findById(orderId);
//                optionalOrder.ifPresent(orderProduct::setOrder);
//
//                Long productId = random.nextLong(1, 301);
//                Optional<Product> optionalProduct = productRepository.findById(orderId);
//                optionalProduct.ifPresent(orderProduct::setProduct);
//
//                orderProduct.setPrice(orderProduct.getProduct().getPrice());
//
//                orderProductRepository.save(orderProduct);
//
//            }
//
//            orderProductRepository.flush();
//        }


    private LocalDateTime getRandomDate(Faker faker, int min, int max) {
        return new java.sql.Date(faker.date().birthday(min, max).getTime()).toLocalDate().atTime(0, 0);
    }

    private boolean hasProbability(int expected, int max) {
        Random random = new Random();
        // random.nextInt(max) = génère un entier aléatoire entre 0 et MAX
        // expected = la proba attendue
        // Si le random est inférieure ou égale à la prob, alors on renvoie true
        return random.nextInt(max) < expected;
    }
}
