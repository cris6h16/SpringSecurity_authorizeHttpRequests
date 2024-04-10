- `permitAll()`  the requests doesn't need any authentication, `Authentication` is never retrieved
- `denyAll()` the requests isn't allowed in any circumstances, `Authentication` is never retrieved
- `hasAuthority(<write> || <read> || <...>)` requires an `Authentication` which has the `GrantedAuthority` that matches with the argument
- `hasRole()` --> A shortcut for `hasAuthority()` that prefixes `ROLE_` or prefix configured
- `hasAnyRole()` --> A shortcut for `hasAnyAuthority()` that prefixes `ROLE_` or prefix configured
- `access` --> A SpEL expression that evaluates to a boolean value
- 