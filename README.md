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
   git clone https://github.com/hey-its-d2t2/MetaImage.git
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

- For more about Apache Commons Imaging, visit the official documentation. <a href ="https://commons.apache.org/proper/commons-imaging/gettingstarted.htm" target='_blank'> Link 1 </a>
<a href = "https://github.com/apache/commons-imaging/blob/master/src/test/java/org/apache/commons/imaging/examples/MetadataExample.java" target = '_blank'> Link 2 </a>

## 🛠 API Endpoints
- Upload Image and Extract Metadata
  - Endpoint: `/api/metadata/upload`
  - Method: `POST`
  - Description: Upload a JPEG image to retrieve EXIF metadata.
- Request:
    - Multipart/form-data with the image file.
- Response:
  - application/json with extracted metadata as key-value pairs.
- Sample Request (using cURL)
  
  ```
  curl -X POST http://localhost:8080/api/metadata/upload -F "image=@/path/to/image.jpg"
  ```
- Example JSON Response

  ```
  {
    "metadata": {
        "Make": "Canon",
        "Model": "Canon EOS 1200D",
        "DateTimeOriginal": "2019:09:04 13:27:40",
        "ExposureTime": "1/60",
        "FNumber": "4",
        "ISO": "800",
        "FocalLength": "55mm",
        "GPSLatitude": "37.7749",
        "GPSLongitude": "-122.4194"
    }
  }
  
  ```

## ❗ Error Handling
Errors are returned in a standardized JSON format:

- 404 Not Found: Requested resource does not exist.
- 400 Bad Request: Invalid image format or unsupported file type.
- 500 Internal Server Error: Unexpected errors.
- Example:

  ```
  {
      "error": "Invalid image format",
      "message": "Please upload a valid JPEG image."
  }
  ```
## 🖼 Screenshots

Screenshots: The UI provides an intuitive display of the metadata in an easy-to-read format.

- Home

  ![EXIF-HOME](https://github.com/user-attachments/assets/f6921216-a4b0-4bf6-b73a-2fe7b8d2f001)
  
- After Uploading Image

![FireShot Capture 039 - Upload Image - localhost](https://github.com/user-attachments/assets/d8e3db61-d4c5-438f-9f1b-48cf2a27e7a4)
![EXTRACTED](https://github.com/user-attachments/assets/40c0506f-0f59-40a1-986b-b1895cd42eea)


## 🛠 Configuration
In `application.properties`, you can set any configuration specifics for logging, server port, etc.
  
  ```
  # Example application.properties configurations
  server.port=8080
  logging.level.org.springframework=INFO
  ```

## 🛠 Contributing
Contributions are welcome! Please follow these steps:

- Fork the repository.
- Create a branch: git checkout -b feature/your-feature.
- Commit your changes: git commit -m "Add feature".
- Push to the branch: git push origin feature/your-feature.
- Submit a pull request for review.

## 📜 License
This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgements
- Apache Commons Imaging: For its robust image metadata extraction.
- Spring Boot: Providing a framework to build a scalable backend.
- Community Contributors: Thanks to everyone who has contributed!

All contributions big or small are highly appreciated! Feel free to improve the documentation, fix bugs, or add new features.

## 👋 Getting Help
 Feel free to reach out to  <a href="mailto:deepsinghkumar01@gmail.com" target="_blank"> Mail</a> or via GitHub issues or discussions.
