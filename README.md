# 📷 Image Metadata Extractor
A Java Spring Boot application for extracting EXIF metadata from JPEG images using the Apache Commons Imaging library. This tool provides an easy-to-use REST API for uploading images and retrieving metadata, presenting data in a clear key-value format for easy frontend rendering.

## 🌟 Features
- 📸 Dynamic Metadata Extraction: Extracts detailed EXIF metadata like camera model, date, GPS coordinates, shutter speed, and more.
- ⚙️ Automatic Parsing: Dynamically handles new metadata fields without needing updates.
- 📝 Clean Key-Value Format Rendering: Renders metadata in a readable format for easy integration into any UI.
- 🛠 Robust API: RESTful endpoints for image upload and metadata retrieval.
- 🚦 Error Handling: Comprehensive error handling to ensure seamless user experience.
## 🧑‍💻 Tech Stack
- Backend: Java 17, Spring Boot 3.3.4
- Dependency: Apache Commons Imaging (for image metadata extraction)
- Build Tool: Maven
- Testing: Postman for API testing 
  - 🔌Prerequisites
    Ensure you have the following installed on your system:
      - Java 17 or higher
      - Maven 3.8+
      - Any IDE (IntelliJ IDEA or Eclipse  IntelliJ IDEA recommended)
## 🚀 Getting Started
1. Clone the Repository
   
   ```
   git clone https://github.com/yourusername/ImageMetadataExtractor.git
   cd ImageMetadataExtractor
    ```
2. Build the Project
   
   ```
   mvn clean install
   ```
3. Run the Application
   
   ```
   mvn spring-boot:run
   ```
4. Access the application
   You can test the application at your localhost by hitting the endpoint at
   
   `http://localhost:8080` [Note  configure your port in `application .properties` file as `server.port=8080` ]
6. Access the API
   You can test the API using Postman or cURL by hitting the endpoint at
   `http://localhost:8080/api/metadata/upload`

## 📑 Implementation Details
This project uses Apache Commons Imaging to parse EXIF data from JPEG files, which allows us to dynamically access metadata fields and display them in key-value format. The library provides flexible methods for working with image metadata, making it ideal for this kind of application.

- For more about Apache Commons Imaging, visit the official documentation. <a href ="https://commons.apache.org/proper/commons-imaging/gettingstarted.htm" target='_blank'> Link 1 and <a href = "https://github.com/apache/commons-imaging/blob/master/src/test/java/org/apache/commons/imaging/examples/MetadataExample.java" target = '_blank'> Link 2

🛠 API Endpoints
Upload Image and Extract Metadata
Endpoint: /api/metadata/upload
Method: POST
Description: Upload a JPEG image to retrieve EXIF metadata.
Request:
multipart/form-data with the image file.
Response:
application/json with extracted metadata as key-value pairs.
Sample Request (using cURL)
