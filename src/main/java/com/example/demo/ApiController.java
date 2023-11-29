package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {
    private final List<String> messages = new ArrayList<>();
    @GetMapping("messages") //curl -X GET localhost:8080/messages
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }
    @PostMapping("messages") //curl -X POST localhost:8080/messages -H "Content-Type: text/plain" -d nikita
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("messages/{index}") //curl -X GET localhost:8080/messages/1
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }
    @DeleteMapping("messages/{index}") //curl -X DELETE localhost:8080/messages/0
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("messages/{index}") //curl -X PUT localhost:8080/messages/0 -H "Content-Type: text/plain" -d "nikita"
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("messages/search/{text}") //curl -X GET localhost:8080/messages/search/nikita
    public ResponseEntity<Integer> searchText(@PathVariable String string){
        for(int i = 0; i < messages.size(); i++){
            if(messages.get(i).contains(string)){
                return ResponseEntity.ok(i);
            }
        }
        return ResponseEntity.ok(-1);
    }

    @GetMapping("messages/count") //curl -X GET localhost:8080/messages/count
    public ResponseEntity<Integer> countMessages(){
        return ResponseEntity.ok(messages.size());
    }

    @PostMapping("messages/{index}/create") //curl -X POST localhost:8080/messages/1/create -H "Content-Type: text/plain" -d nikita
    public ResponseEntity<Void> createMessage(@RequestBody String message, @PathVariable("index") Integer i){
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("messages/search/{text}") //curl -X DELETE localhost:8080/messages/search/nikita
    public ResponseEntity<Void> deleteMessage(@PathVariable String text){
        for(int i = messages.size() - 1; i >= 0; i--){
            if(messages.get(i).contains(text)){
                messages.remove(i);
            }
        }
        return ResponseEntity.accepted().build();
    }

}
