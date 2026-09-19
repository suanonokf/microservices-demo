FROM python:latest
WORKDIR /toxicity-analysis-service
COPY requirements.txt .
RUN pip install --no-cahce-dir -r requirements.txt
COPY . .
CMD ["python","app.py"]