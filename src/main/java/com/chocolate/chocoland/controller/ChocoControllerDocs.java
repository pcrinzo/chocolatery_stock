package com.chocolate.chocoland.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.chocolate.chocoland.dto.ChocoDTO;
import com.chocolate.chocoland.dto.QuantityDTO;
import com.chocolate.chocoland.exception.ChocoAlreadyRegisteredException;
import com.chocolate.chocoland.exception.ChocoNotFoundException;
import com.chocolate.chocoland.exception.ChocoStockExceededException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api("Manages choco stock")
public interface ChocoControllerDocs {

    @ApiOperation(value = "Choco creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success choco creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    ChocoDTO createBeer(ChocoDTO beerDTO) throws ChocoAlreadyRegisteredException;

    @ApiOperation(value = "Returns chocolate found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success chocolate found in the system"),
            @ApiResponse(code = 404, message = "Chocolate with given name not found.")
    })
    ChocoDTO findByName(@PathVariable String name) throws ChocoNotFoundException;

    @ApiOperation(value = "Returns a list of all chocolates registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all chocolates registered in the system"),
    })
    List<ChocoDTO> listBeers();

    @ApiOperation(value = "Delete a chocolate found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success chocolate deleted in the system"),
            @ApiResponse(code = 404, message = "Chocolate with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws ChocoNotFoundException;
}
