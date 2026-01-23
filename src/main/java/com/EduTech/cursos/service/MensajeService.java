package com.EduTech.cursos.service;

import com.EduTech.cursos.model.Mensaje;
import com.EduTech.cursos.model.Usuario;
import com.EduTech.cursos.repository.MensajeRepository;
import com.EduTech.cursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Mensaje enviarMensaje(Long idRemitente, Long idDestinatario, String contenido) {
        Usuario remitente = usuarioRepository.findById(idRemitente)
                .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));

        Usuario destinatario = usuarioRepository.findById(idDestinatario)
                .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));

        Mensaje mensaje = new Mensaje();
        mensaje.setRemitente(remitente);
        mensaje.setDestinatario(destinatario);
        mensaje.setContenido(contenido);

        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> obtenerChat(Long idUsuario1, Long idUsuario2) {
        return mensajeRepository.obtenerConversacion(idUsuario1, idUsuario2);
    }
    public Mensaje obtenerPorId(Long id) {
        return mensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con ID: " + id));
    }
}