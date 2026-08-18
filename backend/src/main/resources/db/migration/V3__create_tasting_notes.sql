-- Static, unchanging flavor descriptors
CREATE TABLE tasting_notes (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(500)
);

-- Placeholder
CREATE TABLE IF NOT EXISTS beans (
    id BIGSERIAL PRIMARY KEY
);

-- beans or whatever we call the entities can have several tasting notes. multiple beans can have the same note
CREATE TABLE bean_tasting_notes (
    bean_id BIGINT NOT NULL REFERENCES beans(id) ON DELETE CASCADE,
    tasting_note_id BIGINT NOT NULL REFERENCES tasting_notes(id) ON DELETE CASCADE,
    PRIMARY KEY (bean_id, tasting_note_id)
);

INSERT INTO tasting_notes (name) VALUES
    ('savory'), ('spice'), ('roasted'), ('grain'),
    ('nutty'), ('sweet'), ('chocolate'), ('dried fruit'),
    ('berry'), ('stone fruit'), ('tropical fruit'), ('grape'),
    ('melon'), ('apple'), ('citrus'), ('floral'),
    ('vegetal'), ('earthy'), ('herb');