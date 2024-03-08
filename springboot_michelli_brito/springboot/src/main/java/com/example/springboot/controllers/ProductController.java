package com.example.springboot.controllers;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products") //mapeamento via HTTP para postagem de dados na base de dados
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel); // faz a conversão do dto para o model que será guardado na base de dados
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel)); //resposta de criação e utilização do método save do JPA Repository para executar operação de guardar dados no banco de dados vinculado.
    }
}
