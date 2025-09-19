package com.example.game.dto;
public record LoginReq(String username, String password) {}
public record LoginResp(String token) {}