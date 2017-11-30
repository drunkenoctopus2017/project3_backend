#octo-config-server
FROM java:8
ADD octo-config-0.0.1-SNAPSHOT.jar config.jar
EXPOSE 9000
CMD ["java", "-jar","config.jar"]

#in bootstrap.yml
#${SPRING_CONFIG_URI:http://ec2-ip:9000}



#in octo-eureka.yml (on config git repo)
#eureka
	#client:
		#serviceUrl:
		#defaultZone: http://ip:8761/octo-eureka