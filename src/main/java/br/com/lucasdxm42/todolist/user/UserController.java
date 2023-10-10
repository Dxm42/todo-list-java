package br.com.lucasdxm42.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
  Modificadores de acesso
  public 
  private
  protected
  final
*/
@RestController
@RequestMapping("/users")
public class UserController {  
  @Autowired
  private IUserRepository userRepository;
/**
 * Tipos de dados
 * Integer (int) numeros inteiros
 * Double (double) numeros 0.000 
 * Float (float) numeros 0.000
 * char (A C)
 * Date (data)
 * Void
 */
@PostMapping("/")
public ResponseEntity create(@RequestBody UserModel userModel){
  var user = this.userRepository.findByUsername(userModel.getUsername());

  if(user != null){
    // Mensagem de erro
    // Status code
    return ResponseEntity.status(400).body("Usuário ja existe");
  }
  var userCreated = this.userRepository.save(userModel);
  return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
}

}
