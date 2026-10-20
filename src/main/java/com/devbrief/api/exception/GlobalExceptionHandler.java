package com.devbrief.api.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.*;
import java.util.stream.Collectors;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(RecursoNaoEncontradoException.class)
 public ResponseEntity<Map<String,Object>> naoEncontrado(RecursoNaoEncontradoException ex) {
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", ex.getMessage()));
 }
 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<Map<String,Object>> validacao(MethodArgumentNotValidException ex) {
  Map<String,String> campos = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
   e -> e.getField(), e -> e.getDefaultMessage()==null ? "valor inválido" : e.getDefaultMessage(), (a,b)->a));
  return ResponseEntity.badRequest().body(Map.of("erro","Dados inválidos","campos",campos));
 }
 @ExceptionHandler({IllegalArgumentException.class, DataIntegrityViolationException.class})
 public ResponseEntity<Map<String,Object>> conflito(RuntimeException ex) {
  return ResponseEntity.badRequest().body(Map.of("erro", ex instanceof IllegalArgumentException ? ex.getMessage() : "Operação inválida: verifique referências e vínculos existentes."));
 }
}
