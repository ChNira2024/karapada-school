pipeline {
    agent any  // Run inside Jenkins container

    environment {
        DOCKER_HOST = 'tcp://host.docker.internal:2375' // Connect to Windows Docker
        DOCKER_IMAGE = 'karapada-school'
        DOCKER_TAG = 'latest'
        DOCKER_CONTAINER = 'karapada-school'
        APP_PORT = '2424'   // Port your app listens on
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ChNira2024/karapada-school.git'
            }
        }

        stage('Build Spring Boot App') {
            steps {
                // Build using Maven inside a temporary Docker container
                sh '''
                    docker run --rm -v $PWD:/app -w /app maven:3.9.2-openjdk-17 \
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
