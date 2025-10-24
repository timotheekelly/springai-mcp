# Spring AI MCP Server with MongoDB

A Model Context Protocol (MCP) server built with Spring AI that exposes MongoDB operations as tools for AI models. This project demonstrates how to create a standardized interface between AI applications and MongoDB, allowing AI models to perform database operations through a simple todo list application.

## What is this?

This is an MCP server that bridges AI models and MongoDB. Instead of AI models needing to understand database connection strings, query syntax, or data modeling, they can simply call tools like `todo-add-task` or `todo-get-tasks`. The server handles all the translation between AI requests and actual MongoDB operations.

The Model Context Protocol (MCP) is an open standard that enables AI applications to securely connect to various data sources and tools. This project shows you how to build an MCP server using Spring AI and Spring Data MongoDB.

## Prerequisites

- **Java 17+** - for running the Spring Boot application
- **Maven** - for building and dependency management
- **MongoDB Atlas account** - M0 free tier works perfectly
  - [Sign up for MongoDB Atlas](https://account.mongodb.com/account/register) if you don't have an account
- **MCP Inspector** (optional, for testing) - install with `npx @modelcontextprotocol/inspector`

## Setup

1. **Clone the repository**
```bash
   git clone <repository-url>
   cd springai-mcp
```

2. **Configure MongoDB connection**
   
   Set your MongoDB connection string as an environment variable:
```bash
   export SPRING_DATA_MONGODB_URI="your-mongodb-atlas-connection-string"
```

3. **Build the project**
```bash
   mvn clean install
```

## Running the Server

Start the Spring Boot application:
```bash
mvn spring-boot:run
```

The MCP server will be available at `http://localhost:8080/mcp`

## Testing with MCP Inspector

1. Install and run the MCP Inspector:
```bash
   npx @modelcontextprotocol/inspector
```

2. In the browser interface:
   - Change transport type to **Streamable HTTP**
   - Enter URL: `http://localhost:8080/mcp`
   - Click **Connect**

3. Navigate to the **Tools** tab to see available tools and test them

## Available Tools

The server exposes three MCP tools for managing todo tasks:

### `todo-add-task`
Adds a new task to the MongoDB database.

**Parameters:**
- `name` (required): The name or description of the task

**Example:** 
```json
{
  "name": "Write documentation"
}
```

### `todo-complete-task`
Marks a task as complete by its name.

**Parameters:**
- `name` (required): The name of the task to mark as complete

**Example:**
```json
{
  "name": "Write documentation"
}
```

### `todo-get-tasks`
Retrieves tasks with optional filtering by completion status.

**Parameters:**
- `filter` (optional): Filter by completion status - accepts `"complete"`, `"incomplete"`, or `"all"` (default)

**Example:**
```json
{
  "filter": "incomplete"
}
```

## Project Structure
```
src/main/java/com/timkelly/springaimcp/
├── Task.java              # MongoDB document model
├── TodoRepository.java    # Spring Data MongoDB repository
├── TodoService.java       # Business logic layer
└── MongoDbTools.java      # MCP tools definition
```

## Configuration

Key configuration in `application.properties`:
```properties
spring.application.name=springai-mcp
spring.ai.mcp.server.name=mongodb-mcp
spring.ai.mcp.server.version=1.0.0

spring.ai.mcp.server.protocol=streamable
spring.ai.mcp.server.stdio=false
spring.ai.mcp.server.type=sync

spring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI}
spring.data.mongodb.database=todo
```

## Learn More

Want to dive deeper into MongoDB's AI capabilities? Check out the [Vector Search Fundamentals course](https://learn.mongodb.com/courses/vector-search-fundamentals) to learn about embeddings, semantic search, and vector databases (earn a skill badge while you're at it).

## Resources

- [Spring AI Documentation](https://docs.spring.io/spring-ai/reference/)
- [Spring AI MCP Documentation](https://docs.spring.io/spring-ai/reference/1.1/api/mcp/mcp-annotations-server.html)
- [Model Context Protocol Specification](https://modelcontextprotocol.io/)
- [MongoDB Atlas](https://www.mongodb.com/atlas)
