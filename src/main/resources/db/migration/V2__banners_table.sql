CREATE TABLE event_banners (
    id UUID PRIMARY KEY,
    event_id UUID NOT NULL,
    image_url VARCHAR(200) NOT NULL,
    is_main BOOLEAN,
    is_second BOOLEAN,
    is_additional BOOLEAN,
    created_at BIGINT NOT NULL,

    CONSTRAINT fk_event_banner_event
    FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE CASCADE
);