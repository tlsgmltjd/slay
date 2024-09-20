package team.asteria.slay.global.security.oauth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import java.io.Serializable

class UserInfo(
    authorities: Collection<out GrantedAuthority>,
    attributes: Map<String, *>,
    nameAttributeKey: String
) : DefaultOAuth2User(authorities, attributes, nameAttributeKey), Serializable
