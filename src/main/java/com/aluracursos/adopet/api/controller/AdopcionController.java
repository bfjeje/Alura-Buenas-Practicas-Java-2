package com.aluracursos.adopet.api.controller;

import com.aluracursos.adopet.api.exceptions.ValidacionException;
import com.aluracursos.adopet.api.model.Adopcion;
import com.aluracursos.adopet.api.model.StatusAdopcion;
import com.aluracursos.adopet.api.repository.AdopcionRepository;
import com.aluracursos.adopet.api.service.AdopcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/adopciones")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid Adopcion adopcion) {
        try {
            this.adopcionService.solicitar(adopcion);
            return ResponseEntity.ok("Adopción solicitada satisfactoriamente!");
        } catch (ValidacionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprobar")
    @Transactional
    public ResponseEntity<String> aprobar(@RequestBody @Valid Adopcion adopcion) {
        this.adopcionService.aprobar(adopcion);
        return ResponseEntity.ok("Solicitud aprobada!");
    }

    @PutMapping("/reprobar")
    @Transactional
    public ResponseEntity<String> reprobar(@RequestBody @Valid Adopcion adopcion) {
        this.adopcionService.reprobar(adopcion);
        return ResponseEntity.ok("Solicitud reprobada.");
    }

}
