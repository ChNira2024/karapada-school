pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'karapada-school'
        DOCKER_TAG = 'latest'
        DOCKER_CONTAINER = 'karapada-school'
        APP_PORT = '2424'
    }

    stages {

        stage('Build Spring Boot App') {
            steps {
                sh '''
                  echo "Workspace contents:"
                  ls -la

                  docker run --rm \
                    -v $PWD:/app \
                    -v $HOME/.m2:/root/.m2 \
                    -w /app \
                    maven:3.9.9-eclipse-temurin-17 \
                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                  docker rm -f ${DOCKER_CONTAINER} || true
                  docker run -d \
                    -p ${APP_PORT}:${APP_PORT} \
                    --name ${DOCKER_CONTAINER} \
                    ${DOCKER_IMAGE}:${DOCKER_TAG}
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
            echo '🌍 App URL: http://localhost:2424'
        }
        failure {
            echo '❌ Pipeline failed! Check logs.'
        }
    }
}
