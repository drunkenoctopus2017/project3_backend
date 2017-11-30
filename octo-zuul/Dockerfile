#octo-config-server
FROM java:8
ADD octo-zuul-0.0.1-SNAPSHOT.jar zuul.jar
EXPOSE 8765
CMD ["java", "-jar", "zuul.jar"]

#in bootstrap.yml
#${SPRING_CONFIG_URI:http://ec2-ip:9000}



#in octo-eureka.yml (on config git repo)
#eureka
	#client:
		#serviceUrl:
			#defaultZone: http://ip:8761/octo-eureka