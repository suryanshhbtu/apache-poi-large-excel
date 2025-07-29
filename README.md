# 📊 Large Excel Writer API

A Spring Boot application that generates and streams large Excel `.xlsx` files using **Apache POI's SXSSFWorkbook** and **XSSFWorkbook**, with Swagger/OpenAPI support.

---

## 🚀 Features

- ✅ Generate Excel files with up to **500,000 rows** and **100 columns**
- ✅ Compare memory-efficient streaming (SXSSF) vs regular (XSSF)
- ✅ REST API with Swagger UI integration
- ✅ Monitor generation time using Spring's `StopWatch`

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3.x
- Apache POI (SXSSF & XSSF)
- Swagger/OpenAPI 3
- Maven

---

## 📦 Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/your-username/large-excel-writer.git
cd large-excel-writer
```

### 2. Build and run

```bash
./mvnw clean spring-boot:run
```

### 3. Access the application

- Swagger UI:  
  👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📂 API Endpoints

### 🔹 SXSSF — Streaming XLSX Writer

- **GET** `/api/excel/download_SXSSF`  
  Streams a large Excel file using `SXSSFWorkbook` (ideal for large datasets)

### 🔹 XSSF — Standard XLSX Writer

- **GET** `/api/excel/download_XSSF`  
  Streams an Excel file using `XSSFWorkbook` (memory-intensive)

---

## 🧪 Performance Tip

- **SXSSFWorkbook**: Uses a **sliding window** and **disk-backed temp files** — recommended for files with **millions of rows**
- **XSSFWorkbook**: Keeps all data in **memory** — best for **small to medium-sized files**

---

## 📸 Example Response

| Type   | Rows    | Columns | File Size (approx) |
|--------|---------|---------|--------------------|
| SXSSF  | 500,000 | 100     | ~150 MB            |
| XSSF   | 50,000  | 100     | ~20 MB             |

---

## 🧰 Dependencies

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.5</version>
</dependency>

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.1.0</version>
</dependency>
```

---

## 📜 License

MIT License © 2025 [Suryansh Srivastava](https://github.com/suryanshhbtu)

---

## 🙋‍♂️ Author

**Suryansh Srivastava**  
🔗 [GitHub](https://github.com/suryanshhbtu) | 🔗 [Portfolio](https://portfolio-next-js-rust.vercel.app)
