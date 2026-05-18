package duoc.cl.ReviewService.service;

import duoc.cl.ReviewService.dto.ResenaDto;
import duoc.cl.ReviewService.model.Resena;
import duoc.cl.ReviewService.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public Resena crearResena(ResenaDto dto) {
        Resena resena = new Resena();
        resena.setJuegoId(dto.getJuegoId());
        resena.setUsuarioEmail(dto.getUsuarioEmail());
        resena.setPuntaje(dto.getPuntaje());
        resena.setComentario(dto.getComentario());
        resena.setFecha(LocalDateTime.now());

        return resenaRepository.save(resena);
    }

    public List<Resena> obtenerResenasPorJuego(int juegoId) {
        return resenaRepository.findByJuegoId(juegoId);
    }

    public Double obtenerPromedioJuego(int juegoId) {
        Double promedio = resenaRepository.calcularPromedioPorJuego(juegoId);
        return promedio != null ? Math.round(promedio * 10.0) / 10.0 : 0.0;
    }
}