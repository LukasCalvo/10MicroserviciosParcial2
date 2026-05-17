package duoc.cl.GameCatalog.service;

import duoc.cl.GameCatalog.model.Juego;
import duoc.cl.GameCatalog.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    public List<Juego> getJuegos() {
        return juegoRepository.findAll();
    }

    public Juego saveJuego(Juego juego) {
        return juegoRepository.save(juego);
    }

    public Juego getJuegoById(int id) {
        return juegoRepository.findById(id).orElse(null);
    }

    public Juego updateJuego(int id, Juego detallesJuego) {
        Juego juegoExistente = juegoRepository.findById(id).orElse(null);

        if (juegoExistente != null) {
            juegoExistente.setNombre(detallesJuego.getNombre());
            juegoExistente.setDescripcion(detallesJuego.getDescripcion());
            juegoExistente.setGenero(detallesJuego.getGenero());
            juegoExistente.setPrecio(detallesJuego.getPrecio());

            return juegoRepository.save(juegoExistente);
        }
        return null;
    }

    public boolean deleteJuego(int id) {
        if (juegoRepository.existsById(id)) {
            juegoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}