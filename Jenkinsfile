pipeline {
    agent any

    environment {
        JENKINS_CONTAINER_NAME = 'new-jenkins-server'
        JENKINS_IMAGE = 'jenkins/jenkins:lts'
        JENKINS_PORT = '8080'
        JENKINS_VOLUME = 'jenkins_home'
    }

    stages {
        stage('Setup Docker') {
            steps {
                script {
                    // Установка Docker на сервере, если его нет
                    sh '''
                    if ! [ -x "$(command -v docker)" ]; then
                      curl -fsSL https://get.docker.com -o get-docker.sh
                      sh get-docker.sh
                    fi
                    '''
                }
            }
        }
        stage('Pull Jenkins Docker Image') {
            steps {
                script {
                    // Скачивание последнего образа Jenkins
                    sh "docker pull ${JENKINS_IMAGE}"
                }
            }
        }
        stage('Cleanup Old Jenkins Container') {
            steps {
                script {
                    // Остановка и удаление старого контейнера, если он существует
                    def existingContainer = sh(script: "docker ps -a -q -f name=${JENKINS_CONTAINER_NAME}", returnStdout: true).trim()

                    if (existingContainer) {
                        echo "Stopping and removing existing container ${JENKINS_CONTAINER_NAME}..."
                        sh "docker stop ${existingContainer} || true"
                        sh "docker rm -f ${existingContainer} || true"
                    } else {
                        echo "No existing container with name ${JENKINS_CONTAINER_NAME} found."
                    }
                }
            }
        }
        stage('Run Jenkins Container') {
            steps {
                script {
                    // Запуск Jenkins контейнера с привязкой портов и монтированием volume
                    sh '''
                    docker run -d --name ${JENKINS_CONTAINER_NAME} \
                      -p ${JENKINS_PORT}:8080 \
                      -p 50000:50000 \
                      -v ${JENKINS_VOLUME}:/var/jenkins_home \
                      ${JENKINS_IMAGE}
                    '''
                }
            }
        }
    }

    post {
        always {
            echo "Jenkins setup and deployment process completed."
        }
    }
}