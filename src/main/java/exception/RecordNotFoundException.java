/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Dell
 */
public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1l;
    
    public RecordNotFoundException(Long id){
        super("Registro não encontrado com o id: " + id);
    }
    
}
