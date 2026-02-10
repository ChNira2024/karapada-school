pipeline {
    agent any

    environment {
        DOCKER_IMAGE     = 'karapada-school'
        DOCKER_TAG       = 'latest'
        DOCKER_CONTAINER = 'karapada-school'
        CONTAINER_PORT   = '2424'
        HOST_PORT        = '2425'
    }

    stages {

        stage('Build Spring Boot App') {
            steps {
                sh '''
                  chmod +x mvnw
                  ./mvnw clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                  docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                '''
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                  docker stop ${DOCKER_CONTAINER} || true
                  docker rm ${DOCKER_CONTAINER} || true

                  docker run -d \
                    -p ${HOST_PORT}:${CONTAINER_PORT} \
                    --name ${DOCKER_CONTAINER} \
                    ${DOCKER_IMAGE}:${DOCKER_TAG}
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
            echo "🌍 Application running at http://localhost:${HOST_PORT}"
        }
        failure {
            echo '❌ Pipeline failed! Check Jenkins logs.'
        }
    }
}
