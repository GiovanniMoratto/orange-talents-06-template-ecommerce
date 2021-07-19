package br.com.zupacademy.giovannimoratto.ecommerce.add_product;

import br.com.zupacademy.giovannimoratto.ecommerce.add_category.CategoryRepository;
import br.com.zupacademy.giovannimoratto.ecommerce.add_user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * @Author giovanni.moratto
 */

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ProductController(ProductRepository repository, CategoryRepository categoryRepository,
                             UserRepository userRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    /* Methods */
    // POST Request - Register a new Product
    @PostMapping("/new-product") // Endpoint
    @Transactional
    public ResponseEntity <?> addNewProduct(@RequestBody @Valid ProductRequest request,
                                            @AuthenticationPrincipal UserDetails userLogged) {
        ProductModel newProduct = request.toModel(categoryRepository, userRepository, userLogged);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

}