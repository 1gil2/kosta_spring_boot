package com.kosta.springbootproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.java.Log;

@Log
@Configuration
@EnableWebSecurity    //security 설정을 담당하는 bean이다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MemberService memberService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();   //Spring Security에서 제공하는 비밀번호 암호화 객체
	}
	
	@Override  //WebSecurity를 통해 HTTP 요청에 대한 웹 기반 보안을 구성
	public void configure(WebSecurity web) throws Exception {
		// 파일 기준은 resources/static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config..........");
		// antMatchers url 패턴에 대한 접근허용
		// permitAll: 모든사용자가 접근가능하다는 의미
		// hasRole : 특정권한을 가진 사람만 접근가능하다는 의미
		http.authorizeRequests() // HttpServletRequest에 따라 접근(access)을 제한
				.antMatchers("/hello/**", "/auth/**").permitAll() //   누구나 접근 허용
				.antMatchers("/admin/**").hasRole("ADMIN") // /admin으로 시작하는 경로는  ADMIN롤을 가진 사용자만  접근 가능(자동으로 ROLE_가 삽입)
				.antMatchers("/manager/**").hasRole("MANAGER")
				.anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
				.and().formLogin() // form 기반으로 인증을 하도록 한다. 로그인 정보는 기본적으로 HttpSession을 이용
				.loginPage("/auth/login") // 로그인 페이지 링크 .... post의 이름이 같다면 loginProcessingUrl생략 
				                                            //스프링시큐리티가 해당주소로 오는 요청을 가로채서 대신한다. 
				.defaultSuccessUrl("/loginSuccess") // 로그인 성공 후 리다이렉트 주소				
				.permitAll()
				.and()
				.logout() // 로그아웃에 관한 설정을 의미
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/auth/login") // 로그아웃 성공시 리다이렉트 주소
				.invalidateHttpSession(true)  // 세션 지우기
				.and().csrf().disable();  //csrf(크로스사이트 위조요청에 대한 설정) 토큰 비활성화 (test시에는 disable권장)            
		http.exceptionHandling().accessDeniedPage("/accessDenied"); // 403 예외처리 핸들링   권한이 없는 대상이 접속을시도했을 때
	}
}
