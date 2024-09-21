package team.asteria.slay.infra.feign.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleTokenResDto (
    @JsonProperty("access_token")
    val accessToken: String
)
