package com.chocolate.chocoland.service;

import lombok.AllArgsConstructor;
import com.chocolate.chocoland.dto.ChocoDTO;
import com.chocolate.chocoland.entity.Choco;
import com.chocolate.chocoland.exception.ChocoAlreadyRegisteredException;
import com.chocolate.chocoland.exception.ChocoNotFoundException;
import com.chocolate.chocoland.exception.ChocoExceededException;
import com.chocolate.chocoland.mapper.ChocoMapper;
import com.chocolate.chocoland.repository.ChocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired)) //lombok implicitamente inclui um construtor
public class ChocoService {

    private static DoubleSummaryStatistics chocoToIncrementStock;
    private final ChocoRepository chocoRepository;
    private final ChocoMapper chocoMapper = ChocoMapper.INSTANCE;

    public ChocoDTO createChoco(ChocoDTO chocoDTO) throws ChocoAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(chocoDTO.getName());
        Choco choco = chocoMapper.toModel(chocoDTO);
        Choco savedChoco = chocoRepository.save(choco);
        return ChocoMapper.toDTO(savedChoco);
    }

    public ChocoDTO findByName(String name) throws ChocoNotFoundException {
        Choco foundChoco = chocoRepository.findByName(name)
                .orElseThrow(() -> new ChocoNotFoundException(name));
        return ChocoMapper.toDTO(foundChoco);
    }

    public List<ChocoDTO> listAll() {
        return chocoRepository.findAll()
                .stream()
                .map(chocoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws ChocoNotFoundException {
        verifyIfExists(id);
        chocoRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws ChocoAlreadyRegisteredException {
        Optional<Choco> optSavedChoco = chocoRepository.findByName(name);
        if (optSavedChoco.isPresent()) {
            throw new ChocoAlreadyRegisteredException(name);
        }
    }

    private static Choco verifyIfExists(Long id) throws ChocoNotFoundException {
        return ChocoRepository.findById(id)
                .orElseThrow(() -> new ChocoNotFoundException(id));
    }

    public static ChocoDTO increment(Long id, int quantityToIncrement) throws ChocoNotFoundException, ChocoExceededException {
        Choco chocoToIncrementStock = verifyIfExists(id);
        int quantityAfterIncrement = quantityToIncrement + chocoToIncrementStock.getQuantity();
        if (quantityAfterIncrement <= chocoToIncrementStock.getMax()) {
            chocoToIncrementStock.setQuantity(chocoToIncrementStock.getQuantity() + quantityToIncrement);
            Choco incrementedChocoStock = ChocoRepository.save(chocoToIncrementStock);
            return ChocoMapper.toDTO(incrementedChocoStock);
        }
        throw new ChocoExceededException(id, quantityToIncrement);
    }
}
