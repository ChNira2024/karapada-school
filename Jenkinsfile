pipeline {
    agent any  // Run inside Jenkins container

    environment {
        // Point to Windows Docker daemon
        DOCKER_HOST = 'tcp://host.docker.internal:2375'
        DOCKER_IMAGE = 'karapada-school'
        DOCKER_TAG = 'latest'
        DOCKER_CONTAINER = 'karapada-school'
        APP_PORT = '2424'   // Port your app listens on
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/ChNira2024/karapada-school.git'
            }
        }

        stage('Build Spring Boot App') {
            steps {
                // Use official Maven + JDK image from Docker Hub (valid tag)
                sh '''
                    docker pull maven:3.9.2-jdk-17-slim || true
                    docker run --rm -v $PWD:/app -w /app maven:3.9.2-jdk-17-slim \
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
                script {
                    // Stop and remove old container if it exists
                    sh "docker rm -f ${DOCKER_CONTAINER} || true"

                    // Run new container with port mapping
                    sh "docker run -d -p ${APP_PORT}:${APP_PORT} --name ${DOCKER_CONTAINER} ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed! Check the logs for details.'
        }
    }
}
