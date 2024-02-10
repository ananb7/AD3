package eventos.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class DataUserConfiguration{
	
	@Bean
	// Recoge el nombre y  la contraseña y lo mete en la BBDD
	public UserDetailsManager usersCustom(DataSource dataSource) {

	JdbcUserDetailsManager users = 
			new JdbcUserDetailsManager(dataSource); 
	users.setUsersByUsernameQuery("select username,password,enabled from Usuarios u where username=?"); 
	users.setAuthoritiesByUsernameQuery("select u.username,p.nombre from Usuario_Perfiles up " +
	 "inner join usuarios u on u.username = up.username " +
			"inner join perfiles p on p.id_perfil = up.id_perfil " +
			"where u.username = ?");

	return users;

	}
	
	// Permitir las rutas
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf -> csrf.disable());
		// Los recursos estáticos no requieren autenticación
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("/static/**", "/css/**", "/js/**", "/json/**", "/images/**").permitAll()
			// Las vistas públicas no requieren autenticación
			.requestMatchers("/registro","/", "/login", "/logout", "/eventos/detalle/**").permitAll()
			.requestMatchers("/eventos/activos", "/eventos/destacados").permitAll()
			// Todas las demás URLs de la Aplicación requieren autenticación
			// Asignar permisos a URLs por ROLES
			.requestMatchers("/eventos/**").hasAnyAuthority("ROLE_CLIENTE") 
			.requestMatchers("/reservas/**").hasAnyAuthority("ROLE_CLIENTE") 
			.anyRequest().authenticated())
		// El formulario de Login no requiere autenticacion
		.formLogin(form -> form.loginPage("/login")
							// Cuando se registra le puedo mandar a donde quiera con esto
							.defaultSuccessUrl("/inicioSesion")
							.permitAll())
		.logout(logout -> logout.logoutSuccessUrl("/").permitAll());
		return http.build();
	}

	
	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/
	

}
