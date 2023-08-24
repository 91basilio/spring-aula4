package next.school.cesar.springaula4.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import next.school.cesar.springaula4.entities.Person;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    ArrayList<Person> listPerson = new ArrayList<Person>();

    @GetMapping // rota que recupera informações (busca)
    public ResponseEntity<List<Person>>getListPerson(){ 
        if (listPerson.size() <= 0){
           return new ResponseEntity<List<Person>>(this.listPerson, HttpStatus.NOT_FOUND);       
         }
           
           return new ResponseEntity<List<Person>>(this.listPerson, HttpStatus.OK); 
        }


    @PostMapping // cria informações 
    public ResponseEntity<Person> create(@RequestBody Person p) {   
        this.listPerson.add(p);
        return new ResponseEntity<Person>(p, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // /person/1231241241  
     public ResponseEntity<Person> update(@RequestBody Person p,@PathVariable(value = "id") int id) 
     {
        for (int i = 0; i < listPerson.size(); i++) { 
            if (listPerson.get(i).getId() == id) 
            { 
            listPerson.get(i).setNome(p.getNome());               
            listPerson.get(i).setIdade(p.getIdade());             
            
                return new ResponseEntity<Person>(listPerson.get(i), HttpStatus.OK);         
            }     
        }  
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
        }

        @DeleteMapping("/{id}")  public ResponseEntity<?> delete(@PathVariable(value = "id") int id) 
        {   
            for (int i = 0; i < listPerson.size(); i++) {  
                if (listPerson.get(i).getId() == id) 
                { 
                this.listPerson.remove(this.listPerson.get(i));           
                return new ResponseEntity<>(HttpStatus.OK);       
             }     
        }  
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
     }

}
    




