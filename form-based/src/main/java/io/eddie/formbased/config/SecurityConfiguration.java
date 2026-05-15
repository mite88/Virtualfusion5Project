package io.eddie.formbased.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * packageName    : io.eddie.formbased.config
 * fileName       : SecurityConfiguration
 * author         : Admin
 * date           : 26. 5. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 15.        Admin       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity
                .cors(AbstractHttpConfigurer::disable) //cors 안함
                .csrf(AbstractHttpConfigurer::disable) //csrf 안함
               /* .formLogin(*/
                        /*
                        input username 파라미터를 다른걸로 바꿀때
                        <label for="username" class="screenreader">Username</label>
                        <input type="text" id="username" name="username" placeholder="Username" required autofocus>
                        */
                        /*form -> form.usernameParameter("loginAccount")
                        .passwordParameter("loginPassword")
                                .loginPage("/login-page") //로그인페이지 진입 url(매핑되어있어야함)
                                .loginProcessingUrl("/login") // 로그인 수행할 url
                                //success(default)ForwardUrl :  추가처리(비동기 처리 등) 화면만바뀜
                                .successForwardUrl("/")//로그인 성공시 임시 진입 url
                                .defaultSuccessUrl("/")//로그인 성공시 진입 url(페이지 이동)
                                .failureForwardUrl("/login-page?error=true")
                                .failureUrl("/login-page?error=true")
                )*/
                .formLogin(Customizer.withDefaults()) //기본셋팅
               /* .logout(
                        logoutConfig -> logoutConfig
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login-page")
                                .invalidateHttpSession(true) //세션 무효화 여부
                                .deleteCookies("JSESSIONID") // 쿠키 삭제
                                .clearAuthentication(true) // 인증정보 없앨 여부
                )*/
                .logout(Customizer.withDefaults())
                //인가
               .authorizeHttpRequests(auth -> auth
                       // permitAll() : 로그인 여부 상관없이 누구나 접근 가능 (GUEST, USER 모두)
                       //.requestMatchers("/", "/login-page", "/signup").permitAll()

                       //ROLE_ 를 자동으로 붙여서 저장할거면 hasRole, hasAnyRole를 사용하면됨 ㅇㅊㅇ
                       // 아니면 hasAuthority, hasAnyAuthority

                       // hasRole() : 특정 신분(Role) 필요 (DB에 ROLE_ADMIN으로 저장되어 있어야 함)
                       //.requestMatchers("/admin/**").hasRole("ADMIN")

                       // hasAnyRole() : 나열된 신분 중 하나만 있으면 접근 가능
                       //.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")

                       // hasAuthority() : 특정 행위 권한(Authority)이 있어야 함 (문자열 그대로 비교)
                       //.requestMatchers("/hello").hasAuthority("write")

                       // hasAnyAuthority() : 나열된 권한 중 하나만 있으면 접근 가능
                       //.requestMatchers("/goodbye").hasAnyAuthority("write", "delete")

                       // authenticated() : 어떤 권한이든 상관없이 "로그인"만 되어있으면 접근 가능
                       //.requestMatchers("/mypage").authenticated()

                       // anonymous() : 로그인 안 한 "익명 사용자"만 접근 가능 (이미 로그인한 사람은 접근 불가)
                       //.requestMatchers("/login").anonymous()

                       // anyRequest() : 위에서 설정한 경로 외의 모든 요청 처리
                       // .permitAll() : 나머지는 다 허용 (테스트 시 편리하지만 보안에 취약)
                       // .authenticated() : 나머지는 무조건 로그인해야 함 (운영 시 권장)
                        .anyRequest().permitAll() // 인증 된사람은 모두 접근 허용
                )
                .build();
    }

    //애플리케이션이 사용할 사용자 정보를 메모리에 수동으로 등록할 때 사용
    //이걸하면 우리가 지정해서 비밀번호가 콘솔에 노출안됨
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //사용자 정보를 DB가 아닌 **서버 메모리(RAM)**에 저장하고 관리하는 클래스

        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("happy")
                //{noop} 암호를 사용하지않을거임
                //그냥 적으면 암호화 안했기때문에 오류난다. 암호화 사용안하게 하던가 암호화를 해주면됨
                //java.lang.IllegalArgumentException: Given that there is no default password encoder configured, each password must have a password encoding prefix. Please either prefix this password with '{noop}' or set a default password encoder in `DelegatingPasswordEncoder`.

                //옛날에는 라이브러리를 불러와서 암호화했지만 제공을해줌(PasswordEncoder)
                .password(passwordEncoder.encode("day"))
                .authorities("read") //읽기권한
                .build());

        return manager;
    }


}
