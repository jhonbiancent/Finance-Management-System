FROM node:22-alpine

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

# Expose Vite default port
EXPOSE 5173

# Host 0.0.0.0 is needed for Docker port mapping to work
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]
