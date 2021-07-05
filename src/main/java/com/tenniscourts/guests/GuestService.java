package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.reservations.Reservation;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GuestService {
	@Autowired
	private final GuestRepository guestRepository;
        
	@Autowired
    private final GuestMapper guestMapper;


    public GuestDTO addGuest(GuestDTO guest) {
        return guestMapper.map(guestRepository.saveAndFlush(guestMapper.map(guest)));
    }

    public GuestDTO findGuestById(Long id) {
        return guestRepository.findById(id).map(guestMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Guest not found");
        });
    }
    
    public List<GuestDTO> findGuestByName(String name) {
        return guestMapper.map(guestRepository.findByName(name));
    }

    public List<GuestDTO> findGuests() {
        return guestMapper.map(guestRepository.findAll());
    }
    
    public GuestDTO deleteGuest(Long id) {
    	Guest guest = guestRepository.findById(id).orElseThrow(() -> 
    	new EntityNotFoundException("Could not find Guest to Delete.")
    	);
        guestRepository.deleteById(guest.getId());
        return guestMapper.map(guest);
        
    }
    
    public GuestDTO updateGuest(Long id) {

    	Guest guest = guestRepository.findById(id).orElseThrow(() -> 
    	new EntityNotFoundException("Could not find Guest to Update.")
    	);
    	return guestMapper.map(guestRepository.save(guest));
    }
    
    


}
