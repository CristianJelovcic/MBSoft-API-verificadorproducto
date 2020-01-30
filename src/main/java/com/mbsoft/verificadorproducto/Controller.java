package com.mbsoft.verificadorproducto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
public class Controller {

    @GetMapping("/verificar")
    public ResponseEntity<Map<String,Object>> verificar(@RequestParam String codigo) {
        if(codigo.isEmpty() || !Pattern.matches("[A-Z]{3}-[0-9]*-[0-9]{1}", codigo)){
            return new ResponseEntity<>(makeMap("ERROR", "codigo invalido"), HttpStatus.BAD_REQUEST);

        }
        boolean valiido = Producto.verificar(codigo);
        return new ResponseEntity<>(verificarDTO(codigo,valiido), HttpStatus.OK);
    }

    private static Map<String, Object> verificarDTO(String codigo, boolean valido) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("PRODUCTO", codigo);
        dto.put("VALIDO", valido);
        return dto;
    }

    private static Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
