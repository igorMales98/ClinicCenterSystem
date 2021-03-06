package com.clinicCenter.controller;

import com.clinicCenter.model.MedicalExaminationRoom;
import com.clinicCenter.model.OperationRoom;
import com.clinicCenter.service.MedicalExaminationRoomService;
import com.clinicCenter.service.OperationRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OperationRoomController {

    @Autowired
    private OperationRoomService operationRoomService;

    @GetMapping("/operationRooms")
    public List<OperationRoom> getAll(){
        List<OperationRoom> rooms = operationRoomService.getAll();
        return rooms;
    }

    @DeleteMapping(value = "/operationRooms/removeRoom/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public void removeById(@PathVariable Long id) {
        OperationRoom room = this.operationRoomService.getById(id);
        this.operationRoomService.removeById(id);
    }

    @PutMapping("/operationRooms")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public int updateOperationRoom( @RequestBody OperationRoom room) {
        return operationRoomService.updateRoom(room);
    }

    @PostMapping("/operationRooms")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public void addOperationRoom(@RequestBody OperationRoom room){
        operationRoomService.save(room);
    }

    @GetMapping("/operationRooms/search")
    @ResponseBody
    public Set<OperationRoom> getSearched(@RequestParam(name = "name") String name, @RequestParam(name = "number") String number) {

        Set<OperationRoom> rooms = null;
        if(number.equals("undefined") || number.equals("null")) {
            rooms = operationRoomService.getRoomsName(name);
        } else {
            rooms = operationRoomService.getRooms(name, Integer.parseInt(number));
        }
        return rooms;
    }

    @GetMapping("getClinicOperationRooms/{clinicId}/{date}/{term}")
    public Collection<OperationRoom> getClinicOperationRooms(@PathVariable Long clinicId, @PathVariable String date,
                                                             @PathVariable String term) throws ParseException {
        return operationRoomService.getClinicOperationRooms(clinicId, date, term);
    }

}
