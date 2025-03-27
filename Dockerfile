FROM openjdk:17-slim

# Install only necessary dependencies (Remove ALSA)
RUN apt-get update && apt-get install -y \
    libfreetype6 fontconfig fonts-dejavu \
    libssl1.1 libnss3 \
    libstdc++6 \
    libasound2 \
    libssl-dev \
    alsa-utils \
    pulseaudio \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the JAR file
COPY target/mutify-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 5050

# Set Java headless mode (no GUI or sound)
ENV JAVA_TOOL_OPTIONS="-Djava.awt.headless=true"

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
