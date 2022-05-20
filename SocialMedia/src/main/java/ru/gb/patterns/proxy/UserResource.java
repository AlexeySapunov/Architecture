package ru.gb.patterns.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequestMapping("/api/v1/user")
@RestController

        this.userService = userService;
    }

    @RequestMapping(value = "/all", produces = "application/json")

        return userService.findAllUsers();
    }

    @RequestMapping(value = "/current", produces = "application/json")

    }
}
