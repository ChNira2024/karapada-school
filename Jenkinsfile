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
                  chmod +x mvnw
                  ./mvnw clean package -DskipTests
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
            echo '🌍 Application running at http://localhost:2424'
        }
        failure {
            echo '❌ Pipeline failed! Check logs.'
        }
    }
}
