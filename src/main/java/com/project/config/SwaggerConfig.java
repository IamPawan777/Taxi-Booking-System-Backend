package com.project.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme( // ✅ Define the security scheme separately
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    scheme = "bearer",
	    bearerFormat = "JWT",
	    in = SecuritySchemeIn.HEADER,
	    description = "Enter JWT token in the format: Bearer <token>"
	)
public class SwaggerConfig {

	@Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Texi Booking API 🚖") // Change title here
                .version("1.0.0")
                .description("""
                	    The Taxi Booking backend project is an innovative 🚀 and robust backend system 🔭 aimed at providing a complete solution for modern transportation applications. The project focuses on delivering a comprehensive set of APIs that cater to all aspects of taxi booking operations, enabling developers to seamlessly build full-featured ride-hailing applications. Key highlights of the Taxi Booking project include:
                	    
                	    🧤 **Complete Booking Ecosystem**: The Taxi Booking project provides a complete end-to-end solution for taxi services. Developers can implement customer apps, driver apps, and admin panels with ready-to-use APIs, allowing them to focus on building great user experiences rather than complex backend logic.
                	    
                	    🔩 **Multi-Role Architecture**: The project offers a sophisticated role-based system supporting Customers, Drivers, and Administrators. Each role has dedicated APIs with appropriate security measures, ensuring proper access control and data isolation while maintaining a unified system architecture.
                	    
                	    🔌 **RESTful API Design**: The Taxi Booking project understands the challenges developers face when building complex booking systems. To address this, the project provides clean RESTful endpoints, comprehensive documentation, standardized error handling, and consistent response formats, simplifying integration and reducing development time.
                	    
                	    🔒 **Advanced Security with JWT**: The project implements robust security measures including JWT-based authentication, ensuring secure communication between clients and the server. This includes token-based session management, secure password handling, and protection against common web vulnerabilities.
                	    
                	    Overall, the Taxi Booking project is a valuable resource for developers seeking to build modern transportation applications. By providing a complete, secure, and scalable backend system, the project empowers developers to create innovative mobility solutions, ultimately contributing to the advancement of digital transportation services.
                	    
                	    ## 🔄 Complete Workflow:
                	    1. **Customer App** 📱: 
                	       - **Customer Page:** Register → Login → Manage Account (update, delete) → Register Successfully
                	       - **Booking Page:** Login → Book Cab → Get Email → Confirm Booking
                	    2. **Driver App** 🚗: 
                	       - **Driver Page:** Register → Login → Manage Account (update, delete) → Register Successfully
                	       - **Cab Page:** Login → Add Cab → Added Successfully
                	    3. **Admin Panel** 💻: Add Administrator Level → Monitor → Manage → Analyze (All Riders, Drivers and Cabs)
                	    
                	    ## 🐞 Project Repository:
                	    Follow [these](https://github.com/IamPawan777/Taxi-Booking-System-Backend) steps to set up the project locally:
                	    1. **Clone the repository** from GitHub 🐞
                	    2. **Configure environment variables ♻**
                	    3. **Set up the database 🔰**
                	    4. **Run the application ✔**
                	    """)                
            );
    }
}




/*

 @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Texi Booking API 🚖") // Change title here
                .version("1.0.0")
                .description("""
                        ### The Taxi Booking API is an innovative 🚀 and robust backend system designed to power modern taxi booking applications.
                        
                        **Key Features:**
                        - **Multi-Role Architecture**: Customer & Driver roles with separate authentication flows
                        - **Complete Booking Ecosystem**: End-to-end ride management from booking to completion
                        - **Real-time Capabilities**: Live tracking and status updates
                        - **JWT Security**: Secure authentication and authorization
                        - **RESTful Design**: Clean, consistent API patterns
                        
                        **Built with:** Spring Boot, Spring Security, JWT, MySQL
                        """)
//                .termsOfService("https://github.com/IamPawan777/taxi-booking-backend")
                .contact(new Contact()
                        .name("Pawan Bisht")
                        .url("https://pawanbisht.netlify.app"))
            );
    }
    
//    private List<Server> getServers() {
//        Server devServer = new Server();
//        devServer.setUrl("http://localhost:8080/api");
//        devServer.setDescription("Local Development Server");
//
//        Server prodServer = new Server();
//        prodServer.setUrl("https://api.taxibooking.example.com/v1");
//        prodServer.setDescription("Production Server");
//
//        return Arrays.asList(devServer, prodServer);
//    }
//    private List<Tag> getTags() {
//        return Arrays.asList(
//            new Tag().name("Authentication").description("User registration and login endpoints"),
//            new Tag().name("Customers").description("Customer-specific operations and profile management"),
//            new Tag().name("Drivers").description("Driver-specific operations and vehicle management"),
//            new Tag().name("Bookings").description("Ride booking, tracking, and management"),
//            new Tag().name("Payments").description("Payment processing and transaction history"),
//            new Tag().name("Admin").description("Administrative operations and system management")
//        );
//    }

*/