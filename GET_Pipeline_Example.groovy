pipeline {
    agent any
    stages {
        stage("Variable Print Through echo") {
            steps {
                script {
                    def response = httpRequest "http://localhost:5000/api/questions/1"
                    def status = response.status
                    def content = response.content
                    def contentJson = readJSON text: content
                    assert status as int == 200
                    println contentJson
                }
            }
        }
    }
}