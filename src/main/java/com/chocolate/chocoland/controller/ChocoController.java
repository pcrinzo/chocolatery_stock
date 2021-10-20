package com.chocolate.chocoland.controller;

import com.chocolate.chocoland.service.ChocoService;
import lombok.AllArgsConstructor;
import com.chocolate.chocoland.dto.ChocoDTO;
import com.chocolate.chocoland.dto.QuantityDTO;
import com.chocolate.chocoland.exception.ChocoAlreadyRegisteredException;
import com.chocolate.chocoland.exception.ChocoNotFoundException;
import com.chocolate.chocoland.exception.ChocoExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/choco")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ChocoController implements ChocoControllerDocs {

    private final ChocoService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChocoDTO createChoco(@RequestBody @Valid ChocoDTO chocoDTO) throws ChocoAlreadyRegisteredException {
        return beerService.createChoco(chocoDTO);
    }

    @Override
    public ChocoDTO createBeer(ChocoDTO beerDTO) throws ChocoAlreadyRegisteredException {
        return null;
    }

    @GetMapping("/{name}")
    public ChocoDTO findByName(@PathVariable String name) throws ChocoNotFoundException {
        ChocoService chocoService = null;
        return chocoService.findByName(name);
    }

    @Override
    public List<ChocoDTO> listBeers() {
        return null;
    }

    @GetMapping
    public List<ChocoDTO> listChoco() {
        ChocoService chocoService = null;
        return chocoService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ChocoNotFoundException {
        beerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public ChocoDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws ChocoNotFoundException, ChocoExceededException {
        return ChocoService.increment(id, quantityDTO.getQuantity());
    }
}
