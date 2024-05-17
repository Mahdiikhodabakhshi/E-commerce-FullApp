package com.tfg.emp.home.infrastructure.rest;



import com.tfg.emp.home.application.Dto.HomeDto;
import com.tfg.emp.home.application.service.HomeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeRestController {

    private final HomeService homeService;

    public HomeRestController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping(value = "/homes/new-homes" , produces = "application/json")
    public ResponseEntity<Page<HomeDto>> getNewHomes(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Sort sort =Sort.by(Sort.Direction.DESC,"uploadDate");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<HomeDto> homeDtos = homeService.getHomeByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(homeDtos);
    }

    @GetMapping(value = "/homes" , produces = "application/json")
    public ResponseEntity<Page<HomeDto>> getHomes(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Sort sort =Sort.by(Sort.Direction.ASC,"title");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<HomeDto> homeDtos = homeService.getHomeByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(homeDtos);
    }

    @GetMapping(value = "/homes/{homeId}" , produces = "application/json")
    public ResponseEntity<HomeDto> getActivity(@PathVariable("homeId") Long homeId) {
        return homeService.getHomeById(homeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/homes" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<HomeDto> createActivity(@RequestBody HomeDto homeDto) {

        HomeDto hDTO = homeService.saveHome(homeDto);
        return new ResponseEntity<>(hDTO , HttpStatus.CREATED);
    }

    @PatchMapping(value = "/homes" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<HomeDto> updateHome(@RequestBody HomeDto homeDto) {
        HomeDto hDTO = homeService.saveHome(homeDto);
        return ResponseEntity.ok(hDTO);
    }

    @DeleteMapping(value = "/homes/{homeId}" , produces = "application/json")
    public ResponseEntity<HomeDto> deleteActivity(@PathVariable("homeId") Long homeId) {
        homeService.deleteHome(homeId);

        return ResponseEntity.noContent().build();
    }


}
