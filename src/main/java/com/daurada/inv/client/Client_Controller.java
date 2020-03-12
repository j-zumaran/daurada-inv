package com.daurada.inv.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daurada.base.DualController;

@RestController
@RequestMapping("/client")
public class Client_Controller extends DualController<NaturalClient, BusinessClient> {

}
