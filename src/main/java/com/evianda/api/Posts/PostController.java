package com.evianda.api.Posts;

import com.evianda.api.Models.User;
import com.evianda.api.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable Long userId) {
        return this.userRepository.findById(userId);
    }

    @GetMapping
    public List<Posts> getProdutos(
            @RequestParam String categoria,
            @RequestParam(required = false) Integer precoMax) {
        // Lógica para buscar produtos com base na categoria e no preço máximo
        return produtoService.buscarPorCategoriaEPreco(categoria, precoMax);
    }
}
