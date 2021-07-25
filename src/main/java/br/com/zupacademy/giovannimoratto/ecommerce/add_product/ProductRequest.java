package br.com.zupacademy.giovannimoratto.ecommerce.add_product;

import br.com.zupacademy.giovannimoratto.ecommerce.add_category.CategoryModel;
import br.com.zupacademy.giovannimoratto.ecommerce.add_category.CategoryRepository;
import br.com.zupacademy.giovannimoratto.ecommerce.add_product.product_features.FeatureRequest;
import br.com.zupacademy.giovannimoratto.ecommerce.add_user.UserModel;
import br.com.zupacademy.giovannimoratto.ecommerce.add_user.UserRepository;
import br.com.zupacademy.giovannimoratto.ecommerce.validations.annotations.ExistsId;
import br.com.zupacademy.giovannimoratto.ecommerce.validations.exception_handler.SearchException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author giovanni.moratto
 */

public class ProductRequest {

    /* Attributes */
    @NotBlank
    private final String name;
    @NotNull
    @Positive
    @DecimalMin("0.01")
    private final BigDecimal price;
    @Min(0)
    @NotNull
    private final Integer stockInformation;
    @NotBlank
    @Size(max = 1000)
    private final String description;
    @NotNull
    @ExistsId(domainClass = CategoryModel.class)
    private final Long idCategory;
    @Valid
    @NotNull
    @Size(min = 3)
    private final List <FeatureRequest> features = new ArrayList <>();

    /* Constructors */
    public ProductRequest(@NotBlank String name, @NotNull @Positive @DecimalMin("0.01") BigDecimal price,
                          @NotNull @Min(0) Integer stockInformation, @NotBlank @Size(max = 1000) String description,
                          @NotNull Long idCategory, @NotNull @Size(min = 3) @Valid List <FeatureRequest> features) {
        this.name = name;
        this.price = price;
        this.stockInformation = stockInformation;
        this.description = description;
        this.idCategory = idCategory;
        this.features.addAll(features);
    }

    /* Methods */
    // Convert ProductRequest.class in ProductModel.class
    public ProductModel toModel(CategoryRepository repository, UserRepository userRepository, UserDetails userLogged) {

        CategoryModel category = repository.findById(idCategory).orElseThrow(() ->
                new SearchException("Category does not exists."));

        UserModel user = userRepository.findByLogin(userLogged.getUsername()).orElseThrow(() ->
                new SearchException("User not allowed!"));

        return new ProductModel(name, price, stockInformation, description, category, user, features);
    }

    /* Getters and Setters */
    public String getName() {
        return name;
    }

}