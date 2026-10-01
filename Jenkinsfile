pipeline {
    agent any
    tools {
        jdk 'JDK-21'
        maven 'Maven-3.9.16'
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')
        DOCKER_USER = 'adharshsanda'
    }
    stages {
        stage('Test Java') {
            steps {
                sh 'java -version'
                sh 'echo $JAVA_HOME'
            }
        }
        stage('Test Docker') {
            steps {
                sh 'docker --version'
            }
        }


        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/adharsh-04/notes-cicd.git'
            }
        }
        stage('Build Backend') {
            steps {
                dir('backend/demo') {
                    sh 'mvn clean package -DskipTests'
                    sh "docker build -t $DOCKER_USER/notes-backend:latest ."
                }
            }
        }
        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh "docker build -t $DOCKER_USER/notes-frontend:latest ."
                }
            }
        }
        stage('Push Images') {
            steps {
                sh "echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKER_USER --password-stdin"
                sh "docker push $DOCKER_USER/notes-backend:latest"
                sh "docker push $DOCKER_USER/notes-frontend:latest"
            }
        }
        stage('Deploy to Minikube') {
            steps {
                sh 'kubectl --kubeconfig=C:/ProgramData/Jenkins/.jenkins/.kube/config apply -f k8s/ --validate=false'
            }
        }
    }
}
