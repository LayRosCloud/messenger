package ru.betrayal.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.betrayal.messenger.services.ParticipantService;

@RestController
@RequestMapping(value = "participants")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService service;


}
