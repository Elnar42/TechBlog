package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Author;
import com.techblog.blogtech.domain.RegisterRequest;
import com.techblog.blogtech.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        Author author = new Author();
        author.setFirstName(request.getFirstName());
        author.setLastName(request.getLastName());
        author.setPassword(passwordEncoder.encode(request.getPassword()));
        author.setEmail(request.getEmail());
        author.setAccountNonExpired(true);
        author.setAccountNonLocked(true);
        author.setCredentialsNonExpired(true);
        author.setEnabled(true);
        author.setRole(request.getRole());
        author.setBibliography(request.getBibliography());
        authorRepository.save(author);
    }

}
